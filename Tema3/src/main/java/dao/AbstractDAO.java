package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * The AbstractDAO class provides a base implementation for data access objects.
 *
 * @param <T> The type of the entity handled by the DAO.
 */
public abstract class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    protected  int protejat;
    private final Class<T> type;

    /**
     * Constructs an AbstractDAO object and determines the type of the entity.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    private String createFindAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" * ");
        sb.append("FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Retrieves a list of all entities from the database.
     *
     * @return The list of entities.
     */
    public ArrayList<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = createFindAll();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return (ArrayList<T>) createObjects(resultSet);
        } catch (SQLException sqlException) {
            LOGGER.log(Level.WARNING, type.getName() + "DAL : get => " + sqlException.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return null;
    }

    /**
     * Retrieves an entity from the database by its ID.
     *
     * @param id The ID of the entity.
     * @return The retrieved entity, or null if not found.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            //return createObjects(resultSet).get(0);
            List<T> obiecte = createObjects(resultSet);
            if(obiecte.size() == 0){
                return null;

            }
            return obiecte.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);

                T instance = null;
                if(protejat==1){    instance = (T) ctor.newInstance();}
                if(protejat==2){ instance = (T) ctor.newInstance();}


                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    private  String createInsertQuery(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        int i=0;
        for (Field field : type.getDeclaredFields()) {
            if(i>0)
            {
                if(i>1){
                    sb.append(", ");
                }
                sb.append(field.getName());
            }
            i++;
        }
        int length=i;
        sb.append(") VALUES (");
        for(i=1; i<length; i++){
            if(i>1){
                sb.append(", ");
            }
            sb.append("?");
        }
        sb.append(")");
        return  sb.toString();
    }
    /**
     * Inserts a new entity into the database.
     *
     * @param t The entity to be inserted.
     * @return The inserted entity.
     */
    public T insert(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        System.out.println(query);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            for(Field field : type.getDeclaredFields()){
                field.setAccessible(true);
                if(!field.getName().equals("id")) {
                    statement.setObject(i, field.get(t));
                    i++;
                }
            }
            System.out.println(statement.toString());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
                Method setIdMethod = type.getMethod("setId",int.class);
                setIdMethod.invoke(t,id);
            }
            return t;
        }catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private String createUpdateQuery(T t, int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE " + type.getSimpleName() + " SET ");

        try {
            String idc = "";
            boolean first = true;

            for (Field field : type.getDeclaredFields()) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                Object value = method.invoke(t);

                if (first) {
                    sb.append(field.getName() + "='" + value.toString() + "'");
                    idc = field.getName() + "='" + id + "'";
                    first = false;
                } else {
                    sb.append(", " + field.getName() + "='" + value.toString() + "'");
                }
            }

            sb.append(" WHERE " + idc);
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
        } catch (IntrospectionException introspectionException) {
            introspectionException.printStackTrace();
        }

        return sb.toString();
    }

    /**
     * Updates an existing entity in the database.
     *
     * @param t  The updated entity.
     * @param id The ID of the entity to be updated.
     * @return True if the update was successful, false otherwise.
     */
    public boolean update(T t, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(t, id);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            LOGGER.log(Level.WARNING, type.getName() + "DAL : update => " + sqlException.getMessage());
            return false;
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return true;
    }

    /**
     * Creates a JTable populated with data from the provided object list.
     *
     * @param objectList The list of objects to be displayed in the table.
     * @return The created JTable.
     */
    public static JTable createJTable2(ArrayList<?> objectList) {
        if (objectList.isEmpty()) {
            return new JTable();
        }
        Class<?> objectClass = objectList.get(0).getClass();
        Field[] fields = objectClass.getDeclaredFields();
        String[] columnNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            columnNames[i] = fields[i].getName();
        }
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (Object object : objectList) {
            Object[] rowData = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                try {
                    rowData[i] = field.get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            tableModel.addRow(rowData);
        }
        JTable jTable = new JTable(tableModel);
        return jTable;
    }

    private String createDeleteQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM " + type.getSimpleName() + " WHERE id= ?" );
        return sb.toString();
    }

    /**
     * Deletes an entity from the database by its ID.
     *
     * @param id The ID of the entity to be deleted.
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteByName " + e.getMessage());
        }
        finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
