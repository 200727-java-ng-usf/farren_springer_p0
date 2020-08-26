package com.revature.revabank.repos;

import com.revature.revabank.models.Account;
import com.revature.revabank.models.AccountType;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.revature.revabank.AppDriver.app;

/**
 * AccountRepository has methods that access the db through SQL query result sets.
 * The query sets create, read, update, or delete information from the accounts table
 * in project0.sql.
 */
public class AccountRepository implements CrudRepository<Account>{

    /**
     * Extract common query clauses into a easily referenced member for re-usability.
     * This base query selects all from the accounts table in schema project0
     */
    private String baseQuery = "SELECT * FROM project0.accounts a ";
//            + "JOIN a " +
//            "ON a.user_id = au.id ";

    /**
     * Constructor
     */
    public AccountRepository() {

//        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
    }

    /**
     * CREATE operation
     * @param account
     * @return
     */
    public Optional<Account> save(Account account) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO project0.accounts (account_type, balance, user_id) " +
                    "VALUES (?, ?, ?)";

            // second parameter here is used to indicate column names that will have generated values
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"}); // Uses the Connection object
            pstmt.setString(1, account.getAccountType().toString()); // the first ? mark and what value it will be in the query
            pstmt.setDouble(2, account.getBalance());
            pstmt.setInt(3, app.getCurrentUser().getId());

            int rowsInserted = pstmt.executeUpdate(); // execute the prepared statement using Connection object that uses the db

            if (rowsInserted != 0) {

                ResultSet rs = pstmt.getGeneratedKeys(); // get the primary keys created by a sequence

                rs.next(); // iterate through the result set
                account.setId(rs.getInt(1)); // get the first column from the db and
                                                        // assign it to the id of the account object

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
    public Set<Optional<Account>> findAll() {

        Optional<Account> _account = Optional.empty();
        Set<Optional<Account>> _accounts = new HashSet<>();

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
            _account = mapResultSet(rs).stream().findAny();
            _accounts.add(_account);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _accounts;
    }

    /**
     * READ operation
     * Find an account by its accountId.
     * Used to authenticate that an account exists in the authenticate
     * method in AccountService.
     * @param id
     * @return
     */
    public Optional<Account> findById(Integer id) {

        Optional<Account> _account = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery + "WHERE a.id = ? AND a.user_id = " + app.getCurrentUser().getId();

            /**
             * PreparedStatement to assign to a ResultSet Object
             */
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            /**
             * ExecuteQuery
             */
            ResultSet rs = pstmt.executeQuery();

            /**
             * Map the result set to the Optional<Account>
             */
            _account = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _account;
    }

    /**
     * READ operation
     * FindAccountWithAppUserId returns one Optional object.
     * This method will be used in the Account Service to find the next instance of an
     * account with a user_id that matches the AppUser's id.
     * @return
     */

    public Optional<Account> findAccountByUserId(Integer userIdFieldOfAccount) {

        Optional<Account> account = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery + "WHERE user_id = ?";

            /**
             * PreparedStatement to assign to a ResultSet Object
             */
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userIdFieldOfAccount);

            /**
             * ExecuteQuery
             */
            ResultSet rs = pstmt.executeQuery();

            /**
             * Map the result set to the Optional<Account>
             */
            account = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return account;
    }

    /**
     * READ operation
     * not active
     * @param userIdFieldOfAccount
     * @return
     */
    public Set<Account> findAllAccountsByUserId(Integer userIdFieldOfAccount) {

        Set<Account> accounts = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery + "WHERE user_id = ?";

            /**
             * PreparedStatement to assign to a ResultSet Object
             */
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userIdFieldOfAccount);

            /**
             * ExecuteQuery
             */
            ResultSet rs = pstmt.executeQuery();

            /**
             * Map the result set to the Optional<Account>
             */
            accounts = mapResultSet(rs).stream().collect(Collectors.toSet());

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return accounts;
    }

    /**
     * UPDATE operation
     * To update the balance column in the accounts table from the project0 schema in the
     * database, updateBalance uses the new balance and the account's id.
     * @param account
     * @return
     */
    public boolean update(Account account) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            /**
             * The only information that can be changed for accounts is the balance.
             */
            String sql = "UPDATE project0.accounts SET balance = " + account.getBalance() + " WHERE id = " + account.getId();

            // second parameter here is used to indicate column names that will have generated values
            PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();

                ResultSet rs = pstmt.getGeneratedKeys();

                rs.next();

//            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return true;
    }

    /**
     * DELETE operation
     * @param id
     * @return
     */
    public boolean deleteById(Integer id) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "DELETE from project0.accounts WHERE id = " + id;

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
     * Convenience method to make READ operations less redundant
     * @param rs
     * @return
     * @throws SQLException
     */
    private Set<Account> mapResultSet(ResultSet rs) throws SQLException {

        Set<Account> accounts = new HashSet<>();

        while (rs.next()) {
            Account temp = new Account();
            temp.setId(rs.getInt("id"));
            temp.setAccountType(AccountType.getByName(rs.getString("account_type")));
            temp.setBalance(rs.getDouble("balance"));
            temp.setUser_id(rs.getInt("user_id"));
            System.out.println(temp);
            accounts.add(temp);
        }

        return accounts;

    }

}
