import Component from 'vue-class-component';
import { Prop, Vue } from 'vue-property-decorator';
import { StatusProcessDeployment } from '@/shared/model/enumerations/status-process-deployment.model';

@Component
export default class AkipShowProcessDeploymentStatusComponent extends Vue {
  @Prop()
  status: StatusProcessDeployment;
}
