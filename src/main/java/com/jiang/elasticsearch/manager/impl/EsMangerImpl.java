package com.jiang.elasticsearch.manager.impl;

import com.jiang.elasticsearch.manager.EsManger;
import com.jiang.elasticsearch.utils.PropertiesUtil;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetAddress;

/**
 * @author yuzj7@lenovo.com
 * @Title:EsMangerImpl 〈esclient〉<br>
 * @create 2018/8/9-16:28
 * @since JDK1.7
 */
@Service
public class EsMangerImpl implements EsManger {

    private static final Logger log = LoggerFactory.getLogger(EsMangerImpl.class);

    private static TransportClient client;
    // 该属性在 往 es中插入数据的时候 用到，见 es 单节点测试用例 EsSingleNodeClusterTest
    private static String indexName;

    static {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @PostConstruct
    public void before() throws Exception {
        try {
            indexName = PropertiesUtil.getProperty("indexname");
            String ESaddress = PropertiesUtil.getProperty("ESaddress");
//            String ESaddress2 = PropertiesUtil.getProperty("ESaddress1");
//            String ESaddress2 = PropertiesUtil.getProperty("ESaddress2");
//            String ESaddress3 = PropertiesUtil.getProperty("ESaddress3");
//            String clusterName =PropertiesUtil.getProperty("cluster.name");
//            Settings settings = Settings.builder().put("cluster.name", clusterName).build();

            Settings settings = Settings.builder().put("cluster.name", "es_demo").put("node.name","test_node").build();


//            client = new PreBuiltTransportClient(settings)
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ESaddress1), Integer.parseInt("9300")))
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ESaddress2), Integer.parseInt("9300")))
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ESaddress3), Integer.parseInt("9300")));

            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.44.100"), Integer.parseInt("9300")));
        } catch (Exception e) {
            log.error("init es client error, e", e);
        }
    }

    @Override
    public TransportClient getClient() {
        return client;
    }
}