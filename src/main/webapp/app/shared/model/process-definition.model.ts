import { StatusProcessDefinition } from '@/shared/model/enumerations/status-process-definition.model';
export interface IProcessDefinition {
  id?: number;
  name?: string | null;
  description?: string | null;
  status?: StatusProcessDefinition | null;
  bpmnProcessDefinitionId?: string | null;
  canBeManuallyStarted?: boolean | null;
}

export class ProcessDefinition implements IProcessDefinition {
  constructor(
    public id?: number,
    public name?: string | null,
    public description?: string | null,
    public status?: StatusProcessDefinition | null,
    public bpmnProcessDefinitionId?: string | null,
    public canBeManuallyStarted?: boolean | null
  ) {}
}
