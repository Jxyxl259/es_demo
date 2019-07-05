package com.jiang.elasticsearch.service.impl;

import com.jiang.elasticsearch.manager.EsManger;
import com.jiang.elasticsearch.service.EsClientService;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/10/12.
 */
@Component("esClientService")
public class EsClientServiceimpl implements EsClientService {
    @Autowired
    private EsManger esManger;
    @Override
    public  TransportClient getClient(){
        return esManger.getClient();
    }
}
