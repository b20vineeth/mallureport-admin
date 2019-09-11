import { combineReducers } from 'redux'
import {galleryReducer} from './galleryReducer';  
import {dataListReducer} from './dataListReducer';  
export default combineReducers({
  dataListReducer,
  galleryReducer
}) 