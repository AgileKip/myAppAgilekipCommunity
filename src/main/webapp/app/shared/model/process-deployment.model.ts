import { ProcessDefinition } from '@/shared/model/process-definition.model';
import { StatusProcessDeployment } from '@/shared/model/enumerations/status-process-deployment.model';

export interface IProcessDeployment {
  id?: number;
  status?: StatusProcessDeployment | null;
  specificationFileContentType?: string | null;
  specificationFile?: string | null;
  camundaDeploymentMessage?: string | null;
  camundaDeploymentId?: string | null;
  camundaProcessDefinitionId?: string | null;
  deployDate?: Date | null;
  activationDate?: Date | null;
  inactivationDate?: Date | null;
  processDefinition?: ProcessDefinition | null;
}

export class ProcessDeployment implements IProcessDeployment {
  constructor(
    public id?: number,
    public status?: StatusProcessDeployment | null,
    public specificationFileContentType?: string | null,
    public specificationFile?: string | null,
    public camundaDeploymentMessage?: string | null,
    public camundaDeploymentId?: string | null,
    public camundaProcessDefinitionId?: string | null,
    public deployDate?: Date | null,
    public activationDate?: Date | null,
    public inactivationDate?: Date | null,
    public processDefinition?: ProcessDefinition | null
  ) {}
}
