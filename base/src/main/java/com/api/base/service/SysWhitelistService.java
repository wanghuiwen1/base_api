package com.api.base.service;
import com.api.base.model.SysWhitelist;
import com.api.core.Service;
import com.api.core.response.Result;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/11/08.
 */
public interface SysWhitelistService extends Service<SysWhitelist> {

    List<SysWhitelist> selectAll();

    Result update(String s, String url);
}
