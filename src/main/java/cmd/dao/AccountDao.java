package cmd.dao;

import cmd.common.AccountStatus;
import cmd.common.AccountType;
import cmd.entity.Account;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class AccountDao extends BaseDao {

    /*登录*/
    public Account login(String username, String password) {
        //连接数据库
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Account account = null;
        try {
            connection = this.getConnection(true);
            String sql = "select id, username, password, name,account_type, account_status from account where username=? and password=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, DigestUtils.md5Hex(password));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = this.extractAccount(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, statement, connection);
        }
        return account;
    }

    /**
     * 注册
     */
    public boolean register(Account account) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean effect = false;
        try {
            connection = this.getConnection(true);
            String sql = "insert into account (username, password, name, account_type, account_status) values (?,?,?,?,?)";
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, account.getUsername());
            statement.setString(2, DigestUtils.md5Hex(account.getPassword()));
            statement.setString(3, account.getName());
            statement.setInt(4, account.getAccountType().getFlag());
            statement.setInt(5, account.getAccountStatus().getFlag());
            effect = (statement.executeUpdate() == 1);
            //获取自增主键到集合resultSet
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                account.setId(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, statement, connection);
        }
        return effect;
    }
    public List<Account> queryAccount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Account> accountList = new ArrayList<>();
        try {
            connection = this.getConnection(true);
            String sql = "select id, username, password, name, account_type, account_status from account";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accountList.add(this.extractAccount(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, statement, connection);
        }
        return accountList;
    }

    private Account extractAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setUsername(resultSet.getString("username"));
        account.setPassword(resultSet.getString("password"));
        account.setName(resultSet.getString("name"));
        account.setAccountType(AccountType.valueOf(resultSet.getInt("account_type")));
        account.setAccountStatus(AccountStatus.valueOf(resultSet.getInt("account_status")));
        return account;
    }
}
