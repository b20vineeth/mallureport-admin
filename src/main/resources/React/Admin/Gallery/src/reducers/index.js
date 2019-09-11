import { combineReducers, applyMiddleware } from 'redux';
import {reducer as formReducer} from 'redux-form';
import { handlingReducer } from "./handlingReducer";



export default combineReducers({  handlingReducer, form:formReducer });