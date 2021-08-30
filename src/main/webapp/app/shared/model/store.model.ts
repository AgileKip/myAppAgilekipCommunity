export interface IStore {
  id?: number;
  name?: string | null;
  city?: string | null;
  address?: string | null;
}

export class Store implements IStore {
  constructor(public id?: number, public name?: string | null, public city?: string | null, public address?: string | null) {}
}
