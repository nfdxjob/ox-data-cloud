package org.dshubs.odc.workflow.config;

import org.dshubs.odc.workflow.identity.CustomGroupEntityManager;
import org.dshubs.odc.workflow.identity.CustomUserEntityManager;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.spring.SpringIdmEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description： Flowable Idm配置
 * @GithubAuthor : zhanglinfu2012
 * @Date: 2022-03-15 22:05
 * @Version: 1.0.0
 * @Copyright: 湖南牛数商智信息科技有限公司
 */
@Configuration
public class FlowableIdmConfig implements EngineConfigurationConfigurer<SpringIdmEngineConfiguration> {

    @Override
    public void configure(SpringIdmEngineConfiguration configuration) {
        configuration.setGroupEntityManager(customGroupEntityManager(configuration));
        configuration.setUserEntityManager(customUserEntityManager(configuration));
    }

    @Bean
    public CustomGroupEntityManager customGroupEntityManager(IdmEngineConfiguration configuration) {
        return new CustomGroupEntityManager(configuration, configuration.getGroupDataManager());
    }

    @Bean
    public CustomUserEntityManager customUserEntityManager(IdmEngineConfiguration configuration) {
        return new CustomUserEntityManager(configuration, configuration.getUserDataManager());
    }
}
