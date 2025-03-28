import {TableEnumVerticalAlign} from "../enum/TableEnum.js";

export interface TableElementPositionVertical {
  isForHeader: boolean,
  textAlign: TableEnumVerticalAlign
}

export interface TableHeader {
  key: string,
  label: string,
  size: string
}

export type DataRow = Record<string, string | number | any>;
