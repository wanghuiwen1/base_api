package com.api.base.config;

import com.api.core.ServiceException;
import com.api.core.response.Result;
import com.api.core.response.ResultEnum;
import com.api.core.response.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;

@ControllerAdvice
public class ExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @org.springframework.web.bind.annotation.ExceptionHandler()
    public @ResponseBody
    Result defaultErrorHandler(HttpServletRequest req, Exception e) {
        logger.error(req.getRequestURI(), e);
        if (e instanceof MaxUploadSizeExceededException) return ResultGenerator.genResult(ResultEnum.UPLOADED_MAX);
        if (e instanceof IllegalArgumentException) return ResultGenerator.genResult(ResultEnum.DATE_ENTRY_ERROR);
        if (e instanceof MissingServletRequestParameterException)
            return ResultGenerator.genResult(ResultEnum.PARAMS_LACK);
        if (e instanceof ConnectException) return ResultGenerator.genResult(ResultEnum.CONNECT_EXCEPTION);
        if (e instanceof HttpRequestMethodNotSupportedException)
            return ResultGenerator.genResult(ResultEnum.INTERNAL_SERVER_ERROR);
        if (e instanceof RequestRejectedException) return ResultGenerator.genResult(ResultEnum.INTERNAL_SERVER_ERROR);
        if (e instanceof ServiceException) return ResultGenerator.genExceptionResult(e);
        return ResultGenerator.genExceptionResult();
    }
}
