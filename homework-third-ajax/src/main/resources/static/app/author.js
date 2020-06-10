import React from 'react';
import ReactDom from "react-dom";

const Header = (props) => (
    <h1>{props.title}</h1>
);

export default class Author extends React.Component {

    constructor() {
        super();
        this.state = {author: []};
    }

    componentDidMount() {
        fetch('/author')
            .then(response => response.json())
            .then(author => this.setState({author}));
    }

    render() {
        return (
            <React.Fragment>
                <Header title={'Author'}/>
                <form id="edit-form" th:action="@{/author(id=${author.id})}" th:method="post" action="author.html">
                    <h1>Author Info:</h1>

                    <div className="row">
                        <label htmlFor="id-input">ID:</label>
                        <input id="id-input" type="text" readOnly="readonly" th:value="${author.id}" value="none"/>
                    </div>

                    <div className="row">
                        <label htmlFor="holder-input">Name:</label>
                        <input id="holder-input" name="name" type="text" th:value="${author.name}"/>
                    </div>

                    <div className="row">
                        <button type="submit">Save</button>
                    </div>
                </form>
            </React.Fragment>
        )
    }
}
ReactDom.render(<Author />, document.getElementById('startPage'));