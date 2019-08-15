package com.tomluo.test.dal;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tomluo.dal.dao.TestExample;
import com.tomluo.dal.dao.TestMapper;
import com.tomluo.dal.enums.TestEnum;
import com.tomluo.test.BaseTest;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James
 * on 16/8/16.
 * Description:
 */
public class TestMapperTest extends BaseTest {
    private final static Logger log = LoggerFactory.getLogger(TestMapperTest.class);
    @Autowired
    private TestMapper mapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    @Test
    public void select() {
//        SqlSession sqlSession =  sqlSessionTemplate.getSqlSessionFactory().openSession();
//        long start = System.currentTimeMillis();
//        System.out.println(mapper.selectByPrimaryKey(1));
//        sqlSession.selectOne("selectSOne");
//        System.out.println("cost "+ (System.currentTimeMillis() - start));
//        start = System.currentTimeMillis();
//        sqlSession.selectOne("selectSOne");
        System.out.println(mapper.selectByPrimaryKey(1));
//        System.out.println("cost "+ (System.currentTimeMillis() - start));
    }

    @Test
    public void insert() {
        com.tomluo.dal.dao.Test test = new com.tomluo.dal.dao.Test();
        test.setName(TestEnum.A.name());
        test.setNums(12);
        log.info("{}", mapper.insert(test));
        log.info("{}", test.getId());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void insertBatch3() {
        long start = System.currentTimeMillis();
        List<com.tomluo.dal.dao.Test> tests = new ArrayList<com.tomluo.dal.dao.Test>();
        for (int i = 0; i < 10; i++) {
            com.tomluo.dal.dao.Test test = new com.tomluo.dal.dao.Test();
//            test.setName(TestEnum.A.name());
            test.setNums(i);
            tests.add(test);
        }
        mapper.insertBatch(tests);
        log.info("cost {}ms", System.currentTimeMillis() - start);
    }

    @Test
    public void pagination() {
        PageHelper.startPage(1,20);
//        List<com.tomluo.dal.dao.Test> tests =  sqlSessionTemplate.selectList("selectAll",null,new RowBounds(2,10));
        List<com.tomluo.dal.dao.Test> tests =  mapper.selectAll();
        PageInfo<Object> page =PageHelper.startPage(1, 10).doSelectPageInfo(new ISelect() {
            public void doSelect() {
                mapper.selectAll();
            }
        });
        log.info(" {}", page);
    }

    @Test
    public void example() {
        TestExample example = new TestExample();
        example.setLimitClause("0,10");
        example.createCriteria().andIdGreaterThan(10);
        mapper.selectByExample(example);
    }
}
