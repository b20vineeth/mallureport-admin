import React, { Component } from 'react'
import TDPanel from './TDPanel';
export default class TableBody extends Component {
    constructor(props) {
        super(props);
 }
    render() { 
        let randomKey=Math.random();
         let   elementBody=[];
         var content = Object.values(this.props.content);
           content.forEach(element => { 
                
             elementBody.push(<TDPanel key={'ma'+element.accessor} element={element}  style={element.style} accessor={element.accessor}></TDPanel>)

         })
         
        return (

             <tr key={'key'+randomKey}>
             {elementBody}
            </tr>
           )
    }
}
