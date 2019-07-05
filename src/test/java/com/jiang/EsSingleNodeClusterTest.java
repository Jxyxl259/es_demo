package com.jiang;

import static org.junit.Assert.assertTrue;

import com.jiang.elasticsearch.manager.EsManger;
import com.jiang.elasticsearch.service.EsClientService;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/applicationContext-beans.xml",
})
public class EsSingleNodeClusterTest {

    private static String INDEX = "order";
    private static String TYPE = "info";

    @Autowired
    private EsClientService esService;

    @Test
    public void shouldAnswerWithTrue() {
//        assertTrue( true );
        String document =
                "{\n" +
                "  \"cName\" : \"测试插入8\",\n" +
                "  \"cAmount\" : \"18.00\",\n" +
                "  \"tags\":[\"couponcode\",\"salescoupon\"]\n" +
                "}";

        TransportClient client = esService.getClient();

        IndexResponse response = client.prepareIndex(INDEX, TYPE, "100006").setSource(document, XContentType.JSON).get();

        //获取索引
        final String _index = response.getIndex();
        //获取类型
        final String _type = response.getType();
        // 文档ID
        String _id = response.getId();
        // 版本
        long _version = response.getVersion();
        // 返回的操作状态
        RestStatus status = response.status();
        System.out.println("索引名称:"+_index+" "+"类型 :" +  _type + " 文档ID："+_id+" 版本 ："+_version+" 返回的操作状态："+status);

    }


}
