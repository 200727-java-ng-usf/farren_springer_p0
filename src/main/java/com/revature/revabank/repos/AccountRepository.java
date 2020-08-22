package com.revature.revabank.repos;

import com.revature.revabank.models.Account;
import com.revature.revabank.models.AppUser;
import com.revature.revabank.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AccountRepository {

    // extract common query clauses into a easily referenced member for reusability.
    private String baseQuery = "SELECT * FROM project0.app_users au " +
            "JOIN project0.user_roles ur " +
            "ON au.role_id = ur.id ";

    public AccountRepository() {
        System.out.println("[LOG] - Instantiating " + this.getClass().getName());
    }

    public Optional<AppUser> findAccountByAccountNumber(int accountNumber) {

        Optional<AppUser> _user = Optional.empty();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = baseQuery + "WHERE username = ? AND password = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, accountNumber);

            ResultSet rs = pstmt.executeQuery();

            _user = mapResultSet(rs).stream().findFirst();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return _user;
    }

    public static Optional<Account> save(Account account) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO project0.accounts (account_type, balance, holder_name, account_number) " +
                    "VALUES (?, ?, ?, ?)";

            // second parameter here is used to indicate column names that will have generated values
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id"});
            pstmt.setString(1, account.getAccountType().toString());
            pstmt.setDouble(2, account.getBalance());
            pstmt.setString(3, account.getHolderName());
            pstmt.setInt(4, account.getAccountNumber());

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

    private Set<AppUser> mapResultSet(ResultSet rs) throws SQLException {

        Set<AppUser> users = new HashSet<>();

        while (rs.next()) {
            AppUser temp = new AppUser();
            temp.setId(rs.getInt("id"));
            temp.setFirstName(rs.getString("account_type"));
            temp.setLastName(rs.getString("balance"));
            temp.setUsername(rs.getString("holder_name"));
            temp.setPassword(rs.getString("account_holder"));
            System.out.println(temp);
            users.add(temp);
        }

        return users;

    }

}
