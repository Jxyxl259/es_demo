package com.jiang.elasticsearch.service;

import org.elasticsearch.client.transport.TransportClient;

/**
 * Created by Administrator on 2017/10/12.
 */
public interface EsClientService {
    public  TransportClient getClient();
}
