package com.tomluo.test;
import com.tomluo.dal.config.DataSourceConfig;
import com.tomluo.dal.config.MybatisConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * Created by James
 * on 16/8/16.
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, MybatisConfig.class})
public abstract class BaseTest {
}
