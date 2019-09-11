import { createStore, combineReducers, applyMiddleware } from 'redux';

import loggerMiddleWare from 'redux-logger'
import HandlingReducer from '../reducers/handlingReducer'
import logger from "redux-logger";
import thunkMiddleWare from 'redux-thunk'

export const store = applyMiddleware(thunkMiddleWare, logger)(window.devToolsExtension
             ? window.devToolsExtension()(createStore)
             : createStore)(HandlingReducer); 


//export default createStore(reducer, applyMiddleware(loggerMiddleWare, thunkMiddleWare));