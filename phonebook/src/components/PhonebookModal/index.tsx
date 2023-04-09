import {Box, Modal, Typography} from '@material-ui/core';
import {useStyles} from './styles';

import {CreateFormPhoneBook} from '@/src/components/Form/CreateFormPhoneBook';
import {EditFormPhoneBook} from '@/src/components/Form/EditFormPhonebook';
import {IPhonebook} from '@/src/@types/phonebook';

interface IPhonebookModalProps {
    open: boolean;
    isEditing: boolean;
    setOpen: (open: boolean) => void;
    setIsEditing: (open: boolean) => void;
    data: IPhonebook;
}

export function PhonebookModal({open, isEditing, setOpen, setIsEditing, data}: IPhonebookModalProps) {
  const classes = useStyles();

  return (
    <Modal
      open={open}
      onClose={() => {
        setOpen(false);
        setIsEditing(false);
      }}
      className={classes.modal}
    >
      <Box className={classes.paper}>
        <Typography variant="h6">
          {isEditing ? 'Edit Entry' : 'New Entry'}
        </Typography>
        {isEditing ? <EditFormPhoneBook result={data}/> : <CreateFormPhoneBook/>}
      </Box>
    </Modal>
  );
}