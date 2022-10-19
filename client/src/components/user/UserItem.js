import React from 'react'
import { Form, Button, Input, Table, Container } from 'semantic-ui-react'

function UserItem({ userId, collectionId, item, comments, handleAddComment, handlePutLike,
    handleDeleteItem, handleInputChange }) {
    let commentList;
    let itemBlock;
    let addCommentBlock;

    if (comments.length === 0) {
        commentList = (
          <Table.Row key='no-comments'>
            <Table.Cell collapsing textAlign='center' colSpan='6'>No comments</Table.Cell>
          </Table.Row>
        )
    } else {
        commentList = comments.map(comment => {
          return (
            <Table.Row key={comment.id}>
              <Table.Cell collapsing>
                <Button
                  circular
                  color='red'
                  size='small'
                  icon='like'
                  onClick={() => handlePutLike(userId, collectionId, item.id, comment.id)}
                />
              </Table.Cell>
              <Table.Cell>{comment.id}</Table.Cell>
              <Table.Cell>{comment.description}</Table.Cell>
              <Table.Cell>{comment.likeAmount}</Table.Cell>
            </Table.Row>
          )
        })
    }

    itemBlock = item.map(it => {
        return (
            <Table.Row key={it.id}>
              <Table.Cell collapsing>
                <Button
                  circular
                  color='red'
                  size='small'
                  icon='delete'
                  onClick={() => handleDeleteItem(userId, collectionId, it.id)}
                />
              </Table.Cell>
              <Table.Cell>{it.id}</Table.Cell>
              <Table.Cell>{it.name}</Table.Cell>
              <Table.Cell>{it.tags}</Table.Cell>
              <Table.Cell>{it.fields}</Table.Cell>
            </Table.Row>
        )
    })

    addCommentBlock = 
    <Form onSubmit={handleAddComment}>
        <Input
          action={{ icon: 'add' }}
          name='addNewComment'
          placeholder='Add comment to this item'
          value={commentDescription}
          onChange={handleInputChange}
        />
    </Form>

    return (
        <Container>
            <Table>
                <Table.Header>
                <Table.Row>
                        <Table.HeaderCell width={1}/>
                        <Table.HeaderCell width={1}>ID</Table.HeaderCell>
                        <Table.HeaderCell width={3}>Name</Table.HeaderCell>
                        <Table.HeaderCell width={4}>Tags</Table.HeaderCell>
                        <Table.HeaderCell width={5}>FIelds</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    {itemBlock}
                </Table.Body>
            </Table>
            <Table compact striped selectable>
                <Table.Header>
                    <Table.Row>
                        <Table.HeaderCell width={1}/>
                        <Table.HeaderCell width={1}>ID</Table.HeaderCell>
                        <Table.HeaderCell width={3}>Description</Table.HeaderCell>
                        <Table.HeaderCell width={4}>LikeAmount</Table.HeaderCell>
                    </Table.Row>
                </Table.Header>
                <Table.Body>
                    {commentList}
                </Table.Body>
            </Table>  
            <Table>
                {addCommentBlock}
            </Table>
        </Container>
    )
}

export default UserItem