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

import static com.revature.revabank.AppDriver.app;

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

//    public Optional<Account> findAccountByAccountId(Integer id) {
//
//        Optional<Account> _account = Optional.empty();
//
//        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
//
//            String sql = baseQuery + "WHERE id = ?";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, id);
//
//            ResultSet rs = pstmt.executeQuery();
//
//            _account = mapResultSet(rs).stream().findFirst();
//
//        } catch (SQLException sqle) {
//            sqle.printStackTrace();
//        }
//
//        return _account;
//    }

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

    private Set<Account> mapResultSet(ResultSet rs) throws SQLException {

        Set<Account> accounts = new HashSet<>();

        while (rs.next()) {
            Account temp = new Account();
            temp.setId(rs.getInt("id"));
            temp.setAccountType((AccountType) rs.getObject("account_type"));
            temp.setBalance(rs.getDouble("balance"));
            temp.setUser_id(rs.getInt("user_id"));
            System.out.println(temp);
            accounts.add(temp);
        }

        return accounts;

    }

}
