/**
 * Copyright (C), 2015-2018, 联想（北京）有限公司
 * FileName: EsManger
 * Author:   yuzj7@lenovo.com
 * Date:     2018/8/9 16:27
 * Description: es
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jiang.elasticsearch.manager;

import org.elasticsearch.client.transport.TransportClient;

/**
 * @author yuzj7@lenovo.com
 * @Title:EsManger 〈es〉<br>
 * @create 2018/8/9-16:27
 * @since JDK1.7
 */
public interface EsManger {
    TransportClient getClient();
}