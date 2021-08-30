package com.mycompany.myapp.process.orderBookProcess;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderBookProcessNotifyClientDelegate implements JavaDelegate {

    private final Logger log = LoggerFactory.getLogger(OrderBookProcessNotifyClientDelegate.class);


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.debug("#########################################################################################");
        log.debug("#########################################################################################");
        log.debug("Inicio de " + OrderBookProcessNotifyClientDelegate.class.getSimpleName());
        log.debug("#########################################################################################");
        log.debug("#########################################################################################");

    }

}
