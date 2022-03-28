package org.dshubs.odc.workflow.config;

import com.google.common.collect.Maps;
import org.dshubs.odc.workflow.common.listener.TaskBeforeListener;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * Flowable流程引擎配置
 *
 * @author 湖南牛数商智信息科技有限公司
 */
@Configuration
public class FlowableEngineConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

    private String flowableFontName = "宋体";

    @Autowired
    private TaskBeforeListener taskBeforeListener;

    @Override
    public void configure(SpringProcessEngineConfiguration engineConfiguration) {
        engineConfiguration.setProcessDiagramGenerator(processDiagramGenerator());
        engineConfiguration.setActivityFontName(flowableFontName);
        engineConfiguration.setLabelFontName(flowableFontName);
        engineConfiguration.setAnnotationFontName(flowableFontName);
        engineConfiguration.setTypedEventListeners(customFlowableListeners());
    }

    @Bean
    public ProcessDiagramGenerator processDiagramGenerator() {
        return new CustomProcessDiagramGenerator();
    }

    private Map<String, List<FlowableEventListener>> customFlowableListeners() {
        Map<String, List<FlowableEventListener>> listenerMap = Maps.newHashMap();
        listenerMap.put(FlowableEngineEventType.TASK_CREATED.name(),
                new ArrayList<>(Collections.singletonList(taskBeforeListener)));
        return listenerMap;
    }
}
