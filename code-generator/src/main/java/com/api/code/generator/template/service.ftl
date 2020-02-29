package ${basePackage}.service;
import ${basePackage}.model.${modelNameUpperCamel};
import ${baseService};
import com.api.core.response.Result;

/**
 * Created by ${author} on ${date}.
 */
public interface ${modelNameUpperCamel}Service extends Service<${modelNameUpperCamel}> {
   Result list(String search, String order, Integer page, Integer size);
}
