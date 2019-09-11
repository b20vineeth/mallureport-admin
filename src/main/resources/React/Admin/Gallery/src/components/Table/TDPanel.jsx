import React, { Component } from 'react'

import * as types from './action-types'
export default class TDPanel extends Component {
    constructor(props) {
        super(props);
        this.operation = this.operation.bind(this)
        
    }
    operation(index)
    {
        console.log(index);
    }
    getTool(element) {
        let tools = [];

        for (let k = 0; k < element.tool.length; k++) { 
            let ops={};
            switch (element.tool[k]) {
                case types.EDIT:
                        ops={"type":types.EDIT,"id":element.id}
                    tools.push(<span title={types.EDIT}  onClick={() => this.operation({ops})} className="glyphicon glyphicon-pencil"></span>); break;
                case types.VIEW:
                        ops={"type":types.VIEW,"id":element.id}
                    tools.push(<span title={types.VIEW}  onClick={() => this.operation({ops})}  className="glyphicon glyphicon-eye-open"></span>); break;
                case types.DELETE:
                        ops={"type":types.DELETE,"id":element.id}
                    tools.push(<span title={types.DELETE}   onClick={() => this.operation({ops})}   className="glyphicon glyphicon-trash"></span>); break;
                case types.DEACTIVATE:
                        ops={"type":types.DEACTIVATE,"id":element.id}
                    tools.push(<span  title={types.DEACTIVATE}  onClick={() => this.operation({ops})}    className="glyphicon glyphicon-eye-close"></span>); break;
                default:
                    tools.push("");
            }
            tools.push(<span>&nbsp;&nbsp;</span>);

        }
        return tools;
    }

    render() {
        
        if (this.props.accessor.length == 0) {
            return (<td className={this.props.style}>{this.getTool(this.props.element)}</td>)
        }
        else {
            return (<td className={this.props.style}>{this.props.accessor}</td>)
        }
    }
}
