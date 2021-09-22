// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.common with an alias.
import Vue from 'vue';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import App from './app.vue';
import Vue2Filters from 'vue2-filters';
import { ToastPlugin } from 'bootstrap-vue';
import router from './router';
import * as config from './shared/config/config';
import * as bootstrapVueConfig from './shared/config/config-bootstrap-vue';
import JhiItemCountComponent from './shared/jhi-item-count.vue';
import JhiSortIndicatorComponent from './shared/sort/jhi-sort-indicator.vue';
import InfiniteLoading from 'vue-infinite-loading';
import HealthService from './admin/health/health.service';
import MetricsService from './admin/metrics/metrics.service';
import LogsService from './admin/logs/logs.service';
import ConfigurationService from '@/admin/configuration/configuration.service';
import ActivateService from './account/activate/activate.service';
import RegisterService from './account/register/register.service';
import UserManagementService from '@/admin/user-management/user-management.service';
import LoginService from './account/login.service';
import AccountService from './account/account.service';

import VueMarkdown from 'vue-markdown';
//import AkipButtonProcessDefinitionInitComponent from '@/shared/akip/akip-button-process-definition-init.vue';
//import AkipButtonProcessDefinitionInstancesComponent from '@/shared/akip/akip-button-process-definition-instances.vue';
//import AkipShowProcessDefinitionComponent from '@/shared/akip/akip-show-process-definition.vue';
//import AkipShowProcessDefinitionStatusComponent from '@/shared/akip/akip-show-process-definition-status.vue';
//import AkipShowProcessDeploymentBpmnModelComponent from '@/shared/akip/akip-show-process-deployment-bpmn-model.vue';
//import AkipShowProcessDeploymentStatusComponent from '@/shared/akip/akip-show-process-deployment-status.vue';
//import AkipShowProcessInstanceComponent from '@/shared/akip/akip-show-process-instance.vue';
//import AkipShowProcessInstanceBpmnModelComponent from '@/shared/akip/akip-show-process-instance-bpmn-model.vue';
//import AkipShowTaskInstanceComponent from '@/shared/akip/akip-show-task-instance.vue';
//import AkipShowTaskInstanceStatusComponent from '@/shared/akip/akip-show-task-instance-status.vue';
//import AkipTableTaskInstancesComponent from '@/shared/akip/akip-table-task-instances.vue';
//import AkipShowProcessInstanceStatusComponent from '@/shared/akip/akip-show-process-instance-status.vue';
// jhipster-pais-needle-add-component-to-main-import - JHipster/Camunda will import components here

// import {
//   AkipShowProcessDefinition,
//   AkipShowTaskInstanceStatus,
//   AkipShowProcessInstanceStatus
// } from 'akip-vue-community';

import Akip from 'akip-vue-community';

import '../content/scss/vendor.scss';
import TranslationService from '@/locale/translation.service';

import UserOAuth2Service from '@/entities/user/user.oauth2.service';
/* tslint:disable */

//import ProcessDefinitionService from '@/entities/process-definition/process-definition.service';
//import ProcessDeploymentService from '@/entities/process-deployment/process-deployment.service';
//import ProcessInstanceService from '@/entities/process-instance/process-instance.service';
//import TaskInstanceService from '@/entities/task-instance/task-instance.service';
// jhipster-needle-add-entity-service-to-main-import - JHipster will import entities services here

/* tslint:enable */
Vue.config.productionTip = false;
config.initVueApp(Vue);
config.initFortAwesome(Vue);
bootstrapVueConfig.initBootstrapVue(Vue);
Vue.use(Vue2Filters);
Vue.use(ToastPlugin);
Vue.use(Akip);

Vue.component('font-awesome-icon', FontAwesomeIcon);
Vue.component('jhi-item-count', JhiItemCountComponent);
Vue.component('jhi-sort-indicator', JhiSortIndicatorComponent);
Vue.component('infinite-loading', InfiniteLoading);

Vue.component('vue-markdown', VueMarkdown);
// Vue.component('akip-show-process-definition', AkipShowProcessDefinition);
// Vue.component('akip-show-process-deployment-bpmn-model', AkipShowProcessDeploymentBpmnModelComponent);
// Vue.component('akip-show-process-deployment-status', AkipShowProcessDeploymentStatusComponent);
// Vue.component('akip-show-process-instance', AkipShowProcessInstanceComponent);
// Vue.component('akip-show-process-instance-bpmn-model', AkipShowProcessInstanceBpmnModelComponent);
// Vue.component('akip-show-process-instance-status', AkipShowProcessInstanceStatus);
// Vue.component('akip-show-task-instance', AkipShowTaskInstanceComponent);
// Vue.component('akip-show-task-instance-status', AkipShowTaskInstanceStatus);
// Vue.component('akip-table-task-instances', AkipTableTaskInstancesComponent);
// jhipster-pais-needle-add-component-to-main - JHipster/Camunda will register components here

const i18n = config.initI18N(Vue);
const store = config.initVueXStore(Vue);

const translationService = new TranslationService(store, i18n);
const loginService = new LoginService();
const accountService = new AccountService(store, translationService, router);

router.beforeEach((to, from, next) => {
  if (!to.matched.length) {
    next('/not-found');
  }

  if (to.meta && to.meta.authorities && to.meta.authorities.length > 0) {
    accountService.hasAnyAuthorityAndCheckAuth(to.meta.authorities).then(value => {
      if (!value) {
        sessionStorage.setItem('requested-url', to.fullPath);
        next('/forbidden');
      } else {
        next();
      }
    });
  } else {
    // no authorities, so just proceed
    next();
  }
});

/* tslint:disable */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>',
  router,
  provide: {
    loginService: () => loginService,
    activateService: () => new ActivateService(),
    registerService: () => new RegisterService(),
    userService: () => new UserManagementService(),
    healthService: () => new HealthService(),
    configurationService: () => new ConfigurationService(),
    logsService: () => new LogsService(),
    metricsService: () => new MetricsService(),

    userOAuth2Service: () => new UserOAuth2Service(),
    translationService: () => translationService,
//    processDefinitionService: () => new ProcessDefinitionService(),
//    processDeploymentService: () => new ProcessDeploymentService(),
//    processInstanceService: () => new ProcessInstanceService(),
//    taskInstanceService: () => new TaskInstanceService(),
    // jhipster-needle-add-entity-service-to-main - JHipster will import entities services here
    accountService: () => accountService,
  },
  i18n,
  store,
});
