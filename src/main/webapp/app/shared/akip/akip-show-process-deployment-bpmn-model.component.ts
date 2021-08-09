import Component from 'vue-class-component';
import { Inject, Prop, Vue } from 'vue-property-decorator';
import Viewer from 'bpmn-js/lib/NavigatedViewer';
import ProcessDeploymentService from "@/entities/process-deployment/process-deployment.service";

@Component
export default class AkipShowProcessDeploymentBpmnModelComponent extends Vue {
    @Inject('processDeploymentService') private processDeploymentService: () => ProcessDeploymentService;

    @Prop()
    processDeploymentId: number;

    mounted() {
        this.tryRenderBpmnModel();
        this.$watch('processDeploymentId', this.tryRenderBpmnModel);
    }

    public tryRenderBpmnModel() {
        if (this.processDeploymentId == undefined) {
            return;
        }

        this.processDeploymentService()
            .findBpmnModel(this.processDeploymentId)
            .then(res => {
                var viewer = new Viewer({ container: '#canvas' });
                viewer.importXML(res.specificationFile);
            },
                err => {
                console.error('Problem findBpmnModel ', err.response);
            });
    }
}
