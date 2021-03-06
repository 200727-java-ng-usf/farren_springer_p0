package com.revature.revabank.repos;

import com.revature.revabank.models.AppUser;
import com.revature.revabank.models.Role;
import com.revature.revabank.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * UserRepository contains methods that access the db through SQL query result sets.
 * The query sets create, read, update, or delete information from the app_users table
 * in project0.sql.
 */
public class UserRepository implements CrudRepository<AppUser>{

    /**
     * Extract common query clauses into a easily referenced member for reusability.
     */
    private String baseQuery = "SELECT * FROM project0.app_users au " +
            "JOIN project0.user_roles ur " +
            "ON au.role_id = ur.id ";

    /**
     * Constructor
     */
    public UserRepository() {

//        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
    }

    /**
     * CREATE operation
     * @param newUser
     */
    public Optional<AppUser> save(AppUser newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO project0.app_users (username, password, first_name, last_name, email, role_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            // second parameter here is used to indicate column names that will have generated values
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getFirstName());
            pstmt.setString(4, newUser.getLastName());
            pstmt.setString(5, newUser.getEmail());
            pstmt.setInt(6, newUser.getRole().ordinal() + 1);

            int rowsInserted = pstmt.executeUpdate(); // returns an int that represents the #rows inserted

            if (rowsInserted != 0) {

                ResultSet rs = pstmt.getGeneratedKeys();

                rs.next();
                newUser.setId(rs.getInt(1));

            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return null;

    }

    /**
     * READ operation
     * @return
     */
    public Set<Optional<AppUser>> findAll() {

        Optional<AppUser> _user = Optional.empty();
        Set<Optional<AppUser>> _users = new HashSet<>();

        /**
         * Try with resources; the resource is the JDB
         */
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery;

            PreparedStatement pstmt = conn.prepareStatement(sql);

            /**
             * ExecuteQuery
             */
            ResultSet rs = pstmt.executeQuery();

            /**
             * Map the result set of the query to the _user Optional
             * to be returned to the findUserByCredentials method.
             */
            _user = mapResultSet(rs).stream().findAny();
            _users.add(_user);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _users;
    }

    /**
     * READ operation
     * @param id
     * @return
     */
    public Optional<AppUser> findById(Integer id) {

        Optional<AppUser> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery + "WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;
    }

    /**
     * READ operation
     * This method will return an optional. It tries to get an instance from the ConnectionFactory
     * and to get a connection to the db.
     * @param username
     * @param password
     * @return
     */
    public Optional<AppUser> findUserByCredentials(String username, String password) {

        Optional<AppUser> _user = Optional.empty();

        /**
         * Try with resources; the resource is the JDB
         */
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery + "WHERE username = ? AND password = ?";

            /**
             * Create a Statement
             *  PreparedStatement
             *      no hardcoded values, instead it is a parameterized query (uses question marks)
             *      the DB receives the parameterized query and compiles it and stores it in its memory
             *      now when we fill in the parameters of our PreparedStatement and send a new query, it
             *      is not compiled again,
             *      instead the DB just fills in the blanks (?) and executes the query works
             *      this prevents us from using up too much memory!
             *      the pre-compiled nature of PreparedStatements also makes the resilient against
             *      SQL Injection (SQLi) attacks
             *      our query is sent and compiled prior to any values being provided
             *      the values of the parameters are provided later
             *      therefore, there is no need for them to be properly escaped!
             */
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            /**
             * ExecuteQuery
             */
            ResultSet rs = pstmt.executeQuery();

            /**
             * Map the result set of the query to the _user Optional
             * to be returned to the findUserByCredentials method.
             */
            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;
    }

    /**
     * READ operation
     * @param username
     * @return
     */
    public Optional<AppUser> findUserByUsername(String username) {

        Optional<AppUser> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery + "WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;

    }

    /**
     * UPDATE method to replace specific update methods
     */
    public boolean update(AppUser appUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "UPDATE project0.app_users "
                    + "SET email = '" + appUser.getEmail() + "', "
                    + "username = '" + appUser.getUsername() + "', "
                    + "password = '" + appUser.getPassword() + "', "
                    + "first_name = '" + appUser.getFirstName() + "', "
                    + "last_name = '" + appUser.getLastName() + "' "
//                    + "', "
//                    + "role_id = " + appUser.getRole() + " " // role is a number
                    + "WHERE id = " + appUser.getId();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate(); //
            pstmt.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return true;
    }

    /**
     * DELETE operation
     */
    public boolean deleteById(Integer id) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "DELETE from project0.app_users WHERE id = " + id;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            rs.next();
            return true;

//            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return false;
    }

    /**
     * To use in READ operations
     * @param rs
     * @return
     * @throws SQLException
     */
    private Set<AppUser> mapResultSet(ResultSet rs) throws SQLException {

        Set<AppUser> users = new HashSet<>();

        /**
         * Extract results, set the temporary AppUser fields, and add the temp AppUser to the Set.
         */
        while (rs.next()) {
            AppUser temp = new AppUser();
            temp.setId(rs.getInt("id"));
            temp.setFirstName(rs.getString("first_name"));
            temp.setLastName(rs.getString("last_name"));
            temp.setUsername(rs.getString("username"));
            temp.setPassword(rs.getString("password"));
            temp.setEmail(rs.getString("email"));
            temp.setRole(Role.getByName(rs.getString("name")));
            System.out.println(temp);
            users.add(temp);
        }

        return users;

    }

}
