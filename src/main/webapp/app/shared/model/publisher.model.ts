export interface IPublisher {
  id?: number;
  name?: string | null;
}

export class Publisher implements IPublisher {
  constructor(public id?: number, public name?: string | null) {}
}
