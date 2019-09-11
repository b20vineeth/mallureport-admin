import * as types from '../actions/action-types'

const INITIAL_STATE = {
    
  datasss:[],
  page:[],
  pageSize:25,
  columns:"",
  fieldData:[],
  isEmptyError:""

}
export const dataListReducer = (state = INITIAL_STATE, action) => {
  
   switch (action.type) {

   case types.DATA_SAVE:
        return {  ...state, datasss:action.data,columns:action.columns,page:action.page,isEmptyError:action.isEmptyError}
  case types.SAVE_FIELD_DATA:
     return {  ...state, fieldData:action.data}
    default:
      return state
  }
}
