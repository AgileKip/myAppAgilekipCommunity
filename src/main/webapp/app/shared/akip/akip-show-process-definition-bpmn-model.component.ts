import Component from 'vue-class-component';
import { Inject, Prop, Vue } from 'vue-property-decorator';
import Viewer from 'bpmn-js/lib/NavigatedViewer';
import ProcessDefinitionService from '@/entities/process-definition/process-definition.service';
import { ProcessDefinition } from '@/shared/model/process-definition.model';

@Component
export default class AkipShowProcessDefinitionBpmnModelComponent extends Vue {
  @Inject('processDefinitionService') private processDefinitionService: () => ProcessDefinitionService;

  @Prop()
  processDefinition: ProcessDefinition;

  mounted() {
    this.$watch('processDefinition', this.renderBpmnModel);
  }

  public renderBpmnModel() {
    if (this.processDefinition == undefined) {
      return;
    }

    this.processDefinitionService()
      .findSpecificationFileContent(this.processDefinition.bpmnProcessDefinitionId)
      .then(
        res => {
          var viewer = new Viewer({ container: '#canvas' });
          viewer.importXML(res.data);
        },
        err => {
          console.error('Problem rendering bpmn ', err.response);
        }
      );
  }
}
