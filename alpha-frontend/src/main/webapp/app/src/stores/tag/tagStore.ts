import {defineStore} from "pinia";
import {ref, Ref} from "vue";
import {
  TagApi, TagApiAddManyTagRequest,
  TagApiAddTagRequest,
  TagApiDeleteTagRequest,
  TagDto, TagList
} from "../../generated/index.js";
import {useCatch} from "../../composables/utils/api/ApiUtils.js";
import {MessageGlobalToastUtils} from "../../composables/utils/message/MessageGlobalUtils.js";
import {AxiosResponse} from "axios";
import {useLoadingStore} from "../loading/loadingStore.js";

export const useTagStore = defineStore('tagStore', () => {
  const tagApi: TagApi = new TagApi()
  const loadingStore = useLoadingStore();

  const tagList: Ref<Array<TagDto>, Array<TagDto>> = ref<TagDto>([]);

  function addTag(newTag: TagDto) {
    const tagDto: TagApiAddTagRequest = {
      tagDto: {
        labelTag: newTag.label,
        color: newTag.color
      },
    }

    loadingStore.activeLoading();
    useCatch(
      tagApi.addTag(tagDto).then((axiosData: AxiosResponse<TagDto, any>) => {
        tagList.value.push(axiosData.data);

        loadingStore.disabledLoading();
        MessageGlobalToastUtils.successMessage(`Tag "${axiosData.data.label}" a bien été ajouté`);
      })
    )
  }

  function addTagList(newTagList: Array<TagDto>) {
    console.log('TEST : ', newTagList);
    const tagDto: TagApiAddManyTagRequest = {
      tagList: {
        tagList: newTagList
      }
    }

    console.log(tagDto);
    loadingStore.activeLoading();
    useCatch(
        tagApi.addManyTag(tagDto).then((axiosData: AxiosResponse<TagList, any>) => {
        tagList.value.push(axiosData.data);

        console.log(tagList.value);
        loadingStore.disabledLoading();
        MessageGlobalToastUtils.successMessage(`Les Tags "${axiosData.data}" ont bien été ajouté`);
      })
    )
  }

  function removeTag(tag: TagDto) {
    const tagDto: TagApiDeleteTagRequest = {
      idTag: tag.id,
    }

    loadingStore.activeLoading();
    useCatch(
      tagApi.deleteTag(tagDto).then((axiosData) => {
        loadingStore.disabledLoading();
        if (axiosData.data.result) {
          tagList.value = tagList.value.filter(tagItem => tagItem.id !== tag.id);
          MessageGlobalToastUtils.successMessage(`Tag "${tag.label}" a bien été supprimé`);
        } else {
          MessageGlobalToastUtils.warningMessage(`Tag ${tag.label} n'a pas été supprimé correctement`);
        }
      })
    )
  }

  function reloadTag() {
    loadingStore.activeLoading();
    useCatch(
      tagApi.getAllTag().then((axiosData) => {
        tagList.value = axiosData.data;
        loadingStore.disabledLoading();
      })
    )
  }

  return {
    tagList,
    addTag,
    addTagList,
    reloadTag,
    removeTag
  }
});
