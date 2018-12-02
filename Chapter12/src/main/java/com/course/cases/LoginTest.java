package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DataBaseUril;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

public class LoginTest {

    //private static Log LOG = LogFactory.getLog(LoginTest.class);

   @BeforeTest(groups = "loginTrue",description = "测试准备工作，创建Httpclient对象等")
    public void beforeTest(){
       TestConfig.getUserInfoUrl= ConfigFile.getUrl(InterfaceName.GETUSERINFO);
       TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSER);
       TestConfig.getUserListUrl= ConfigFile.getUrl(InterfaceName.GETUSERLIST);
       TestConfig.loginUrl=ConfigFile.getUrl(InterfaceName.LOGIN);
       TestConfig.updateUserInfoUrl=ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);

       TestConfig.httpClient= new DefaultHttpClient();


    }
   @Test(groups = "loginTrue",description = "用户登录成功接口")
    public void loginTrue() throws IOException {
       SqlSession session = DataBaseUril.getSqlSession();
       LoginCase loginCase = session.selectOne("loginCase", 1);
       System.out.println(loginCase.toString());
       System.out.println(TestConfig.loginUrl);

   }
  @Test(groups = "loginFalse",description = "登录失败用例")
   public void loginFalse() throws IOException {
      SqlSession session = DataBaseUril.getSqlSession();
      LoginCase loginCase = session.selectOne("loginCase", 2);
      System.out.println(loginCase.toString());
      System.out.println(TestConfig.loginUrl);

   }
}
