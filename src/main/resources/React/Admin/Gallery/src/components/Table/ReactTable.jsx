import React, { Component } from 'react'
import ReactTableRow from '../../containers/Table/ReactTableRowContainer'
import Pagination from './Pagination'
export default class ReactTable extends Component {
    constructor(props) {
        super(props);
        this.state = ({
            tableHead: []
        });

    }
    componentDidMount() {
        this.props.dataLoad(this.props.content, this.props.columns, this.props.page, this.props.isEmptyError);

    }

    render() {

        return (
            <React.Fragment>

                <div className="row pad">

                    <table className="table table-fixed">

                        <ReactTableRow />

                    </table>
                    <div align="right">
                        <ul className="pagination pagination-sm">
                            <Pagination page={this.props.page} />

                        </ul>
                    </div>
                </div>
            </React.Fragment>
        )
    }

}
