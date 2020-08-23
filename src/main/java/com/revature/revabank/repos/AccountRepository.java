package com.revature.revabank.repos;

import com.revature.revabank.models.Account;
import com.revature.revabank.models.AccountType;
import com.revature.revabank.util.ConnectionFactory;

import javax.swing.text.html.Option;
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
 * AccountRepository contains methods that access the db through SQL query result sets.
 */
public class AccountRepository {

    /**
     * Extract common query clauses into a easily referenced member for reusability.
     * This base query selects all from the accounts table in schema project0
     */
    private String baseQuery = "SELECT * FROM project0.accounts a " +
            "JOIN project0.app_users au " +
            "ON a.user_id = au.id ";

    /**
     * Breadcrumbs
     */
    public AccountRepository() {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
    }

    /**
     * Find an account by its accountId.
     * Used to authenticate that an account exists in the authenticate
     * method in AccountService.
     * @param accountId
     * @return
     */
    public Optional<Account> findAccountByAccountId(Integer accountId) {

        Optional<Account> _account = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery + "WHERE a.id = ?";

            /**
             * PreparedStatement to assign to a ResultSet Object
             */
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, accountId);

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
     * FindAccountWithAppUserId returns one Optional object.
     *
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


    public static Optional<Account> save(Account account) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO project0.accounts (account_type, balance, user_id) " +
                    "VALUES (?, ?, ?)";

            // second parameter here is used to indicate column names that will have generated values
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
            pstmt.setString(1, account.getAccountType().toString());
            pstmt.setDouble(2, account.getBalance());
            pstmt.setInt(3, app.getCurrentUser().getId());

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {

                ResultSet rs = pstmt.getGeneratedKeys();

                rs.next();
                account.setId(rs.getInt(1));

            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return null;
    }

    /**
     * To update the balance column in the accounts table from the project0 schema in the
     * database, updateBalance uses the new balance and the account's id.
     * @param account
     * @param balance
     * @param accountId
     * @return
     */
    public static Optional<Account> updateBalance(Double balance, Integer accountId) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "UPDATE project0.accounts SET balance = " + balance + " WHERE id = " + accountId;

            // second parameter here is used to indicate column names that will have generated values
            PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();

                ResultSet rs = pstmt.getGeneratedKeys();

                rs.next();

//            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return null;
    }

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
