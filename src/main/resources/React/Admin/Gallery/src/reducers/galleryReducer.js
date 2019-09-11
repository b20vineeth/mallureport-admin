import * as types from '../actions/action-types'

const INITIAL_STATE = {
  galleryData: [],
  isLoading:true, 
  datas:[],
  page:"",
  isloaded:false

}
 


export const galleryReducer = (state = INITIAL_STATE, action) => {  
  switch (action.type) {

    case types.LIST_SUCCESS:
      return Object.assign({}, state, { galleryData: action.gallery,page:action.page,isLoading:false,isloaded:action.isLoaded}) 
    
     
    default:
      return state
  }
}
