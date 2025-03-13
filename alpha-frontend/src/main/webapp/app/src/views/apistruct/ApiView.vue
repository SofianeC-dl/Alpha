<script setup lang="ts">
import {
  ImageIllustrationApi,
  type ImageIllustrationApiAddImageIllustrationRequest,
  type ImageIllustrationApiDeleteImageIllustrationRequest,
  type ImageIllustrationApiGetImageIllustrationRequest,
  type ImageIllustrationApiUpdateImageIllustrationRequest,
  ImageProjectApi,
  type ImageProjectApiAddImageProjectRequest,
  type ImageProjectApiAddManyImageProjectRequest,
  type ImageProjectApiDeleteImageProjectRequest,
  type ImageProjectApiGetImageProjectRequest,
  type ImageProjectApiUpdateImageProjectRequest,
  ProjectApi,
  type ProjectApiAddProjectRequest,
  type ProjectApiAddProjectWithImageIllustrationRequest,
  type ProjectApiDeleteProjectRequest,
  type ProjectApiGetProjectRequest,
  type ProjectApiUpdateProjectRequest,
  TagApi,
  type TagApiAddTagRequest,
  type TagApiDeleteTagRequest,
  type TagApiGetTagRequest,
  type TagApiUpdateTagRequest,
} from '@/generated/index.js'
import type { AxiosPromise } from 'axios'
import { MessageGlobalToastUtils } from '@/composables/utils/message/MessageGlobalUtils.js'
import { type Ref, ref } from 'vue'
import { useCatch } from '@/composables/utils/api/ApiUtils.js'
import {useModalStore} from "@/stores/modal/modalStore.js";
const tagApi: TagApi = new TagApi()
const imageIllustrationApi: ImageIllustrationApi = new ImageIllustrationApi()
const projectApi: ProjectApi = new ProjectApi()
const imageProjectApi: ImageProjectApi = new ImageProjectApi()

const projectId: Ref<number, number> = ref(1)
const tagId: Ref<number, number> = ref(1)
const imageIllustrationId: Ref<number, number> = ref(1)
const imageProjectId: Ref<number, number> = ref(1)

const modalStore = useModalStore();
const throwError = () => {
  MessageGlobalToastUtils.errorMessage('TEST')
}

const throwSucces = () => {
  MessageGlobalToastUtils.successMessage('TEST')
}

const throwInfo = () => {
  MessageGlobalToastUtils.infoMessage('TEST')
}

const throwWarn = () => {
  MessageGlobalToastUtils.warningMessage('TEST')
}

const consoleLog = (axiosResult: AxiosPromise, label: string) => {
  useCatch(
    axiosResult.then((axiosData) => {
      MessageGlobalToastUtils.successMessage(`${label} a bien fonctionné, id : ${axiosData.data.id}`);

    })
  )
}

const getTag = () => {
  const tagDto: TagApiGetTagRequest = {
    idTag: tagId.value,
  }

  consoleLog(tagApi.getTag(tagDto), 'Tag Get')
}

const getAllTags = () => {
  consoleLog(tagApi.getAllTag(), 'Tag Get All')
}

const postTag = () => {
  const tagDto: TagApiAddTagRequest = {
    tagDto: {
      label: 'Tag',
    },
  }
  consoleLog(tagApi.addTag(tagDto), 'Tag Add')
}

const putTag = () => {
  const tagDto: TagApiUpdateTagRequest = {
    idTag: tagId.value,
    tagDto: {
      id: tagId.value,
      label: 'updateTag',
    },
  }
  consoleLog(tagApi.updateTag(tagDto), 'Tag update')
}

const deleteTag = () => {
  const tagDto: TagApiDeleteTagRequest = {
    idTag: tagId.value,
  }

  consoleLog(tagApi.deleteTag(tagDto), 'Tag delete')
}

const getProject = () => {
  const projectDto: ProjectApiGetProjectRequest = {
    idProject: projectId.value,
  }
  consoleLog(projectApi.getProject(projectDto), 'Project Get')
}

const getAllProjects = () => {
  consoleLog(projectApi.getAllProject(), 'Project Get All')
}

const postProject = () => {
  const projectDto: ProjectApiAddProjectRequest = {
    projectDto: {
      description: 'description',
      idImageIllustration: imageIllustrationId.value,
      tagSet: [tagId.value],
    },
  }
  consoleLog(projectApi.addProject(projectDto), 'Project Add')
}

const postProjectWithImageIllustration = () => {
  const projectDto: ProjectApiAddProjectWithImageIllustrationRequest = {
    projectWithImageIllustrationDto: {
      projectDto: {
        description: 'description',
        tagSet: [tagId.value],
      },
      imageIllustrationDto: {
        datas: 'testData',
        tagSet: [tagId.value],
      },
    },
  }
  consoleLog(projectApi.addProjectWithImageIllustration(projectDto), 'Project Add')
}

const putProject = () => {
  const projectDto: ProjectApiUpdateProjectRequest = {
    idProject: projectId.value,
    projectDto: {
      id: projectId.value,
      description: 'description',
      idImageIllustration: imageIllustrationId.value,
      tagSet: [tagId.value],
    },
  }
  consoleLog(projectApi.updateProject(projectDto), 'Project update')
}

const deleteProject = () => {
  const projectDto: ProjectApiDeleteProjectRequest = {
    idProject: projectId.value,
  }

  consoleLog(projectApi.deleteProject(projectDto), 'Project delete')
}

const getImageIllustration = () => {
  const imageIllustrationDto: ImageIllustrationApiGetImageIllustrationRequest = {
    idImageIllustration: imageIllustrationId.value,
  }
  consoleLog(
    imageIllustrationApi.getImageIllustration(imageIllustrationDto),
    'ImageIllustration Get',
  )
}

const getAllImageIllustrations = () => {
  consoleLog(imageIllustrationApi.getAllImageIllustration(), 'ImageIllustration Get All')
}

const postImageIllustration = () => {
  const imageIllustrationDto: ImageIllustrationApiAddImageIllustrationRequest = {
    imageIllustrationDto: {
      datas: 'testData',
      tagSet: [tagId.value],
    },
  }
  consoleLog(
    imageIllustrationApi.addImageIllustration(imageIllustrationDto),
    'ImageIllustration Add',
  )
}

const putImageIllustration = () => {
  const imageIllustrationDto: ImageIllustrationApiUpdateImageIllustrationRequest = {
    idImageIllustration: imageIllustrationId.value,
    imageIllustrationDto: {
      id: imageIllustrationId.value,
      datas: 'testData',
      tagSet: [tagId.value],
    },
  }
  consoleLog(
    imageIllustrationApi.updateImageIllustration(imageIllustrationDto),
    'ImageIllustration update',
  )
}

const deleteImageIllustration = () => {
  const imageIllustrationDto: ImageIllustrationApiDeleteImageIllustrationRequest = {
    idImageIllustration: imageIllustrationId.value,
  }

  consoleLog(imageIllustrationApi.deleteImageIllustration(imageIllustrationDto), 'Project delete')
}

const getImageProject = () => {
  const imageProjectDto: ImageProjectApiGetImageProjectRequest = {
    idImageProject: imageProjectId.value,
  }
  consoleLog(imageProjectApi.getImageProject(imageProjectDto), 'ImageProject Get')
}

const getAllImageProjects = () => {
  consoleLog(imageProjectApi.getAllImageProject(), 'ImageProject Get All')
}

const postImageProject = () => {
  const imageProjectDto: ImageProjectApiAddImageProjectRequest = {
    imageProjectDto: {
      datas: 'testData',
      idProject: projectId.value,
      tagSet: [tagId.value],
    },
  }
  consoleLog(imageProjectApi.addImageProject(imageProjectDto), 'ImageProject Add')
}

const postManyImageProject = () => {
  const imageProjectDto: ImageProjectApiAddManyImageProjectRequest = {
    imageProjectList: {
      imageProjectList: [
        {
          datas: 'testData',
          idProject: projectId.value,
          tagSet: [tagId.value],
        },
        {
          datas: 'testData',
          idProject: projectId.value,
          tagSet: [tagId.value],
        },
      ],
    },
  }

  consoleLog(imageProjectApi.addManyImageProject(imageProjectDto), 'ImageProject Add')
}

const putImageProject = () => {
  const imageProjectDto: ImageProjectApiUpdateImageProjectRequest = {
    idImageProject: imageProjectId.value,
    imageProjectDto: {
      id: imageProjectId.value,
      datas: 'testData',
      idProject: projectId.value,
      tagSet: [tagId.value],
    },
  }
  consoleLog(imageProjectApi.updateImageProject(imageProjectDto), 'ImageProject update')
}

const deleteImageProject = () => {
  const imageProjectDto: ImageProjectApiDeleteImageProjectRequest = {
    idImageProject: imageProjectId.value,
  }

  consoleLog(imageProjectApi.deleteImageProject(imageProjectDto), 'Project delete')
}
</script>

<template>
  <div>
    <button @click="throwError">Déclencher une erreur</button>
    <button @click="throwSucces">Déclencher un succes</button>
    <button @click="throwInfo">Déclencher une info</button>
    <button @click="throwWarn">Déclencher un warn</button>

    <div>
      <a>###################################################################</a>
    </div>

    <button @click="() => modalStore.open({
      title: 'test',
      size: 'md',
      body: 'body test'
    })" >Modal </button>

    <div>
      <a>###################################################################</a>
    </div>

    <div class="inputApi">
      <label>Project ID : </label>
      <input type="text" v-model="projectId" />
    </div>
    <div class="inputApi">
      <label>Tag ID : </label>
      <input type="text" v-model="tagId" />
    </div>
    <div class="inputApi">
      <label>Image Illustration ID : </label>
      <input type="text" v-model="imageIllustrationId" />
    </div>
    <div class="inputApi">
      <label>Image Project ID : </label>
      <input type="text" v-model="imageProjectId" />
    </div>
  </div>
  <div>
    <button @click="getTag">Tag Get</button>
    <button @click="getAllTags">Tag Get all</button>
    <button @click="postTag">Tag Post</button>
    <button @click="putTag">Tag Put</button>
    <button @click="deleteTag">Tag Delete</button>
  </div>
  <div>
    <a>###################################################################</a>
  </div>
  <div>
    <button @click="getImageIllustration">ImageIllustration Get</button>
    <button @click="getAllImageIllustrations">ImageIllustration Get all</button>
    <button @click="postImageIllustration">ImageIllustration Post</button>
    <button @click="putImageIllustration">ImageIllustration Put</button>
    <button @click="deleteImageIllustration">ImageIllustration Delete</button>
  </div>
  <div>
    <a>###################################################################</a>
  </div>
  <div>
    <button @click="getProject">Project Get</button>
    <button @click="getAllProjects">Project Get all</button>
    <button @click="postProject">Project Post</button>
    <button @click="postProjectWithImageIllustration">Project Post With Image Illustration</button>
    <button @click="putProject">Project Put</button>
    <button @click="deleteProject">Project Delete</button>
  </div>
  <div>
    <a>###################################################################</a>
  </div>
  <div>
    <button @click="getImageProject">ImageProject Get</button>
    <button @click="getAllImageProjects">ImageProject Get all</button>
    <button @click="postImageProject">ImageProject Post</button>
    <button @click="postManyImageProject">ImageProject Post Many</button>
    <button @click="putImageProject">ImageProject Put</button>
    <button @click="deleteImageProject">ImageProject Delete</button>
  </div>
</template>

<style scoped>
div {
  text-align: center;
  display: flex;
  flex-direction: column;
}

.inputApi {
  width: 100%;
}

.inputApi label {
  width: 100%;
  flex-direction: row;
  text-align: justify;
}

.inputApi input {
  width: 20px;
  text-align: center;
}

button {
  margin: 5px;
}
</style>
