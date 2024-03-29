import React from "react";
import TableCell from "@material-ui/core/TableCell";
import TableRow from "@material-ui/core/TableRow";
import {
  showCartDlg,
  deleteCartItem,
  updateCartItemQnt
} from "../../Redux/Actions";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import { Link } from "react-router-dom";

const CartRow = props => {
  let { item } = props;
  return (
    <TableRow>
      <TableCell>
        <Link to={`/details/${item.prod_id}`}>
          <div
            onClick={() => {
              props.dispatch(showCartDlg(false));
            }}
          >
            {item.prodName}
          </div>
        </Link>
      </TableCell>
      <TableCell>{item.prodPrice}</TableCell>
      <TableCell>
        <TextField
          type="number"
          style={{ width: 40 }}
          value={item.quantity}
          onChange={e => {
            let quantity = parseInt(e.target.value, 10);
            if (quantity < 0) return;
            props.dispatch(
              updateCartItemQnt({
                id: item.prod_id,
                quantity
              })
            );
          }}
        />
      </TableCell>
      <TableCell>
        <Button
          color="secondary"
          onClick={() => {
            props.dispatch(deleteCartItem(item.prod_id));
          }}
        >
          Delete
        </Button>
      </TableCell>
    </TableRow>
  );
};

export default CartRow;
