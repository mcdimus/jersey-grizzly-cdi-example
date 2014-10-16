package com.example;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.jboss.weld.environment.se.Weld;

public class WebServiceBinder extends AbstractBinder {

  @Override
  protected void configure() {
    BeanManager bm = getBeanManager();
    bind(getBean(bm, NewClass.class)).to(NewClass.class);
  }

  private BeanManager getBeanManager() {
    return new Weld().initialize().getBeanManager();
  }

  private <T> T getBean(BeanManager bm, Class<T> clazz) {
    Bean<T> bean = (Bean<T>) bm.getBeans(clazz).iterator().next();
    CreationalContext<T> ctx = bm.createCreationalContext(bean);
    return (T) bm.getReference(bean, clazz, ctx);
  }
}
