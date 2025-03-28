<script setup lang="ts">
import {defineProps, PropType} from 'vue'
import {TableEnumTextAlign, TableEnumVerticalAlign} from "@/composables/enum/TableEnum.js";
import {DataRow, TableHeader} from "@/composables/object/TableObject.js";

const props = defineProps({
  data: {
    type: Array as PropType<DataRow[]>,
    default: () => [],
  },
  header: {
    type: Array as PropType<TableHeader[]>,
    default: () => []
  },
  textAlignHeader: {
    type: String as PropType<TableEnumTextAlign>,
    default: TableEnumTextAlign.LEFT
  },
  verticalAlignHeader: {
    type: String as PropType<TableEnumVerticalAlign>,
    default: TableEnumVerticalAlign.TOP
  },
  textAlignData: {
    type: String as PropType<TableEnumTextAlign>,
    default: TableEnumTextAlign.LEFT
  },
  verticalAlignData: {
    type: String as PropType<TableEnumVerticalAlign>,
    default: TableEnumVerticalAlign.TOP
  }
})
</script>

<template>
    <table>
      <thead>
      <tr>
        <th
          :class="`header-table-text-align-${textAlignHeader} header-table-vertical-align-${verticalAlignHeader}`"
          :style="{'--size-header': col.size }"
          v-for="col in header"
          :key="col.key">
          <!-- utiliser
            <template #header-[columns.key]>
              [personalised_name]
            </template> dans le parent -->
          <slot :name="'header-' + col.key">{{ col.label }}</slot>
        </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(row, index) in data" :key="index">
        <td
          v-for="col in header"
          :key="col.key"
          :style="{'--size-header': col.size }"
          :class="`header-table-text-align-${textAlignData} header-table-vertical-align-${verticalAlignData}`">
          <!-- utiliser
            <template #cell-[columns.key]={ [dataRow] } = >
              <span :style="{ color: [dataRow].age > 30 ? 'red' : 'green' }">
                {{ [dataRow].age }} ans
              </span>
            </template> dans le parent -->
          <slot :name="'cell-' + col.key" :row="row">
            {{ row[col.key] }}
          </slot>
        </td>
      </tr>
      </tbody>
    </table>
</template>

<style scoped lang="scss">
@use '@/assets/css/index' as mylib;

.header-table-text-align-left {
  text-align: left;
}

.header-table-text-align-center {
  text-align: center;
}

.header-table-text-align-right {
  text-align: right;
}

.header-table-vertical-align-top {
  vertical-align: top;
}

.header-table-vertical-align-middle {
  vertical-align: middle;
}

.header-table-vertical-align-right {
  vertical-align: bottom;
}

table {
  width: 100%;

  thead {
    tr {
      th {
        width: var(--size-header);
        white-space: normal;
        overflow-wrap: break-word;
      }
      th:first-child {
        border-left: mylib.$table-border-size solid mylib.$color-font-global;
        border-top: mylib.$table-border-size solid mylib.$color-font-global;
        border-bottom: mylib.$table-border-size solid mylib.$color-font-global;
      }
      th:not(:first-child):not(:last-child) {
        border-top: mylib.$table-border-size solid mylib.$color-font-global;
        border-bottom: mylib.$table-border-size solid mylib.$color-font-global;
      }
      th:last-child {
        border-right: mylib.$table-border-size solid mylib.$color-font-global;
        border-top: mylib.$table-border-size solid mylib.$color-font-global;
        border-bottom: mylib.$table-border-size solid mylib.$color-font-global;
      }
    }
  }

  tbody {
    tr {
      td {
        width: var(--size-header);
        max-width: var(--size-header);
        white-space: normal;
        overflow-wrap: break-word;
      }
      td:first-child {
        border-left: mylib.$table-border-size solid mylib.$color-font-global;
        border-top: mylib.$table-border-size solid mylib.$color-font-global;
        border-bottom: mylib.$table-border-size solid mylib.$color-font-global;
      }
      td:not(:first-child):not(:last-child) {
        border-top: mylib.$table-border-size solid mylib.$color-font-global;
        border-bottom: mylib.$table-border-size solid mylib.$color-font-global;
      }
      td:last-child {
        border-right: mylib.$table-border-size solid mylib.$color-font-global;
        border-top: mylib.$table-border-size solid mylib.$color-font-global;
        border-bottom: mylib.$table-border-size solid mylib.$color-font-global;
      }
    }
  }
}
</style>
