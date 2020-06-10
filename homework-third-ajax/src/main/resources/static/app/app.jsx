import React from "react";
import ReactDom from "react-dom";
import {Link, Route, Switch} from "react-router-dom";
import { Router } from "react-router-dom";

// import AuthorsPage from "./authorsPage";

class App extends React.Component {

    render() {
        return <React.Fragment>
            <h1>Sections: </h1>
            <table>
                <Router>
                <thead>
                <tr>
                    <th>Section</th>
                    <th>Add button</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <Link to={"/showAuthors"}>Authors</Link>
                        {/*<a href={"/showAuthors"}>Authors</a>*/}
                    </td>
                    <td>
                        <Link to={"/addAuthor"}>Authors</Link>
                        {/*<a href={"/addAuthor"}>Add author</a>*/}
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href={"/showBook"}>Books</a>
                    </td>
                    <td>
                        <a href={"/addBook"}>Add book</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href={"/showComment"}>Comments</a>
                    </td>
                    <td>
                        <a href={"/addComment"}>Add comment</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href={"/showGenre"}>Genres</a>
                    </td>
                    <td>
                        <a href={"/addGenre"}>Add genre</a>
                    </td>
                </tr>
                </tbody>
                    <Switch>
                        <Route path={"/showAuthors"}>
                            {/*<AuthorsPage />*/}
                        </Route>
                        <Route path={"/addAuthor"}>
                            {/*<AuthorsPage />*/}
                        </Route>
                    </Switch>
                </Router>
            </table>
        </React.Fragment>;
    }
}

ReactDom.render(<App />, document.getElementById('startPage'));
