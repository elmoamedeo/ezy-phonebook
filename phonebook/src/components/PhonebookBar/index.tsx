import styles from '../../styles/PhonebookList.module.css';
import SearchIcon from '@material-ui/icons/Search';
import {
  InputAdornment,
  TextField,
} from '@material-ui/core';

interface IPhonebookBar {
  query: string;
  handleQueryChange: (event: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => void
}

export const PhonebookBar = ({ query, handleQueryChange }: IPhonebookBar) => {
  return (
    <div className={styles.search_bar_row}>
      <TextField
        InputProps={{
          startAdornment: (
            <InputAdornment position="start">
              <SearchIcon />
            </InputAdornment>
          ),
        }}
        placeholder="Search for contact by last name..."
        variant="outlined"
        value={query}
        onChange={handleQueryChange}
        fullWidth
        style={{
          borderRadius: 10
        }}
      />
    </div>
  );
};