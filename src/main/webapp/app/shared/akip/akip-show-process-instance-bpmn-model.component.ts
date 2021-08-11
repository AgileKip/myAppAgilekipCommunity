import Component from 'vue-class-component';
import { Inject, Prop, Vue } from 'vue-property-decorator';
import Viewer from 'bpmn-js/lib/NavigatedViewer';
import ProcessInstanceService from '@/entities/process-instance/process-instance.service';

@Component
export default class AkipShowProcessInstanceBpmnModelComponent extends Vue {
  @Inject('processInstanceService') private processInstanceService: () => ProcessInstanceService;

  @Prop()
  processInstanceId: number;

  mounted() {
    this.tryRenderBpmnModel();
    this.$watch('processInstanceId', this.tryRenderBpmnModel);
  }

  public tryRenderBpmnModel() {
    if (this.processInstanceId == undefined) {
      return;
    }

    this.processInstanceService()
      .findBpmnModel(this.processInstanceId)
      .then(
        res => {
          var viewer = new Viewer({ container: '#canvas' });
          viewer.importXML(res.processDeploymentBpmnModel.specificationFile).then(() => {
            var canvas = viewer.get('canvas');
            res.completedTasksDefinitionKeys.forEach(taskDefinitionKey => {
              canvas.addMarker(taskDefinitionKey, 'akip-completed-task');
            });
            res.runningTasksDefinitionKeys.forEach(taskDefinitionKey => {
              canvas.addMarker(taskDefinitionKey, 'akip-doing-task');
            });
          });
        },
        err => {
          console.error('Problem rendering bpmn ', err.response);
        }
      );
  }
}
