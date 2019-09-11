//entry point
import React from 'react'
import ReactDOM from 'react-dom'
import App from './components/GalleryApp'
//import 'bootstrap/dist/css/bootstrap.min.css'  
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap.js'; 
import {store} from './store'
import './index.css' 
import { Provider } from 'react-redux'
ReactDOM.render(
<Provider store={store}>
    <App />
 </Provider>,
    document.getElementById('container'))