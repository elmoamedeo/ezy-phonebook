import React, {useContext, useLayoutEffect} from 'react';
import {useForm} from 'react-hook-form';
import {zodResolver} from '@hookform/resolvers/zod';
import {z} from 'zod';
import {IPhonebook} from '@/src/@types/phonebook';
import {Button, FormControl, Input, InputLabel} from '@material-ui/core';
import {PhonebookContext} from '@/src/context/PhonebookContext';

interface EditFormPhonebookProps {
    result: IPhonebook;
}

export function EditFormPhoneBook({result}: EditFormPhonebookProps) {
  const {handleEditEntry, phonebooks} = useContext(PhonebookContext);

  const Phonebook = z.object({
    id: z.number().min(1, {message: 'Id is necessary'}).optional(),
    firstName: z.string().min(1, {message: 'FirstName obg'}),
    lastName: z.string().min(1, {message: 'lastName obg'}),
    phoneNumber: z.string().min(1, {message: 'phoneNumber obg'})
  });

    type Phonebook = z.infer<typeof Phonebook>;

    const {
      register,
      handleSubmit,
      formState: {errors},
      setValue,
    } = useForm<IPhonebook>({
      mode: 'all',
      reValidateMode: 'onChange',
      resolver: zodResolver(Phonebook),
    });

    useLayoutEffect(() => {
      setValue('id', result.id);
      setValue('firstName', result.firstName);
      setValue('lastName', result.lastName);
      setValue('phoneNumber', result.phoneNumber);
    }, [phonebooks]);


    const onSubmit = (data: Phonebook) => {
      Phonebook.parse(data);
      const response = {
        ...data,
        id: result?.id
      };
      handleEditEntry(response);
    };

    return (
      <form onSubmit={handleSubmit(onSubmit)}>
        <FormControl fullWidth margin="normal">
          <InputLabel htmlFor="id">Id</InputLabel>
          <Input
            id="id"
            type="number"
            {...register('id')}
            disabled
          />
          {errors.id?.message && (
            <p style={{color: 'red'}}>{errors.id?.message}</p>
          )}
        </FormControl>
        <FormControl fullWidth margin="normal">
          <InputLabel htmlFor="firstName">First Name</InputLabel>
          <Input
            id="firstName"
            type="text"
            {...register('firstName')}
          />
          {errors.firstName?.message && (
            <p style={{color: 'red'}}>{errors.firstName?.message}</p>
          )}
        </FormControl>
        <FormControl fullWidth margin="normal">
          <InputLabel htmlFor="lastName">Last Name</InputLabel>
          <Input
            id="lastName"
            type="text"
            {...register('lastName')}
          />
          {errors.lastName?.message && (
            <p style={{color: 'red'}}>{errors.lastName?.message}</p>
          )}
        </FormControl>
        <FormControl fullWidth margin="normal">
          <InputLabel htmlFor="phoneNumber">Phone Number</InputLabel>
          <Input
            id="phoneNumber"
            type="tel"
            {...register('phoneNumber')}
          />
          {errors.phoneNumber?.message && (
            <p style={{color: 'red'}}>{errors.phoneNumber?.message}</p>
          )}
        </FormControl>
        <Button
          type="submit"
          variant="contained"
          color="primary"
        >
                Edit Entry
        </Button>
      </form>
    );
}