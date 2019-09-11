import React, { Component } from 'react'
import ThContainer from '../../containers/table/ThContainer'
import TdContainer from '../../containers/table/TdContainer'
import TDEmpty from './TDEmpty'
export default class ReactTableRow extends Component {
  constructor(props) {
    super(props);


  }
  render() {
    let randomKey=Math.random();
        
    var elementHead = [];
    var arr = Object.values(this.props.columnList);
    let i=0;
    arr.forEach(element => {
      i++;
      elementHead.push(<ThContainer  key={"ThContainer"+i} headerStyle={element.headerStyle} Header={element.Header} />)

    })
    var elementBody = [];
      i=0;
    var fieldData = Object.values(this.props.fieldData);
    if (fieldData.length > 0) {
      fieldData.forEach(element => {
        i++;
        elementBody.push(<TdContainer  key={"TdContainer"+i} content={element} />)

      })
    }
    else {
      elementBody.push(<TDEmpty key={"TDEMPTY"} message={this.props.isEmptyError} column={arr.length} />)
    }

    return (
      <React.Fragment>
        <thead>
          <tr>
            {
              elementHead
            }
          </tr>
        </thead>



        <tbody>

        {
            elementBody
          }

        </tbody>
      </React.Fragment>


    )
  }
}
