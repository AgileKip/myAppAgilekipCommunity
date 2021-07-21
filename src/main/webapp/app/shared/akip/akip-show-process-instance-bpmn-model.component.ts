import Component from 'vue-class-component';
import { Inject, Prop, Vue } from 'vue-property-decorator';
import { ProcessInstance } from '@/shared/model/process-instance.model';
import Viewer from 'bpmn-js/lib/NavigatedViewer';
import ProcessDefinitionService from '@/entities/process-definition/process-definition.service';
import ProcessInstanceService from '@/entities/process-instance/process-instance.service';
import { ITaskInstance } from '@/shared/model/task-instance.model';

@Component
export default class AkipShowProcessInstanceBpmnModelComponent extends Vue {
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;
  @Inject('processInstanceService') private processInstanceService: () => ProcessInstanceService;

  @Prop()
  processInstance: ProcessInstance;

  mounted() {
    this.$watch('processInstance', this.tryRenderSpecificationFile);
  }

  public tryRenderSpecificationFile() {
    if (this.processInstance == undefined) {
      return;
    }

    this.processInstanceService()
      .findTaskInstances(this.processInstance.id)
      .then(
        res => {
          var taskInstances: ITaskInstance[] = res.data;
          var runningTasks = taskInstances.filter(ti => ti.status == 'NEW' || ti.status == 'ASSIGNED').map(ti => ti.taskDefinitionKey);

          this.processDefinitionService()
            .findSpecificationFileContent(this.processInstance.processDefinition.bpmnProcessDefinitionId)
            .then(
              res => {
                var viewer = new Viewer({ container: '#canvas' });
                viewer.importXML(res.data).then(() => {
                  var canvas = viewer.get('canvas');
                  runningTasks.forEach(taskDefinitionKey => {
                    canvas.addMarker(taskDefinitionKey, 'highlight');
                  });
                });
              },
              err => {
                console.error('Problem rendering bpmn ', err.response);
              }
            );
        },
        err => {
          console.error('Problem rendering bpmn ', err.response);
        }
      );
  }
}
