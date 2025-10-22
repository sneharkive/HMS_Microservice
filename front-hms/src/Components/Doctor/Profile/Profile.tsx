import {
  Avatar,
  Divider,
  Paper,
  Table,
  Button,
  TextInput,
  NumberInput,
  Select,
  Modal,
} from "@mantine/core";
import { IconCamera, IconDeviceFloppy, IconEdit } from "@tabler/icons-react";
import { useState } from "react";
import { useSelector } from "react-redux";
import { DateInput } from "@mantine/dates";
import { doctorDepartment, doctorSpecializations } from "../../../Data/DropdownData";
import { useDisclosure } from "@mantine/hooks";

const doctor = {
  name: "Sneha Roy",
  email: "sneha.roy@example.com",
  dob: "2003-01-25",
  phone: "+91 98765 43210",
  address: "123 Park Street, Kolkata, West Bengal",
  licenseNo: "1234-5678-9012",
  specialization: "Radiology",
  department: "Radiology",
  totalExp: "2"
};

const Profile = () => {
  const user = useSelector((state: any) => state.user);
  const [editMode, setEditMode] = useState(false);
  const [opened, {open, close}] = useDisclosure(false);
  

  return (
    <div className="p-10">
      <div className="flex justify-between items-center">
        <div className="flex gap-5 items-center">
          <div className="flex flex-col items-center gap-3">
            <Avatar
              variant="filled"
              src="/avatar.png"
              size={150}
              alt="My Profile"
            />

            {editMode && <Button
              onClick={open}
              variant="filled"
              size="sm"
              leftSection={<IconCamera />}
            >
              Upload
            </Button>}
          </div>
          <div className="flex flex-col gap-2">
            <div className="text-3xl font-medium text-neutral-900">
              {user.name}
            </div>
            <div className="text-xl text-neutral-700">{user.email}</div>
          </div>
        </div>

        {!editMode ? (
          <Button
            onClick={() => setEditMode(true)}
            variant="filled"
            size="md"
            leftSection={<IconEdit />}
          >
            Edit
          </Button>
        ) : (
          <Button
            onClick={() => setEditMode(false)}
            variant="filled"
            size="md"
            leftSection={<IconDeviceFloppy />}
          >
            Save
          </Button>
        )}
      </div>
      <Divider my="xl" />

      <div>
        <div className="text-2xl font-medium text-neutral-900">
          Personal Information
        </div>

        <Paper shadow="sm" radius="md" p="md" withBorder>
          <Table
            striped
            stripedColor="primary.2"
            verticalSpacing="sm"
            highlightOnHover
            withRowBorders={false}
          >
            <Table.Tbody>
              <Table.Tr>
                <Table.Td className="text-md font-medium">
                  Date of Birth
                </Table.Td>
                {editMode ? (
                  <Table.Td>
                    <DateInput placeholder="Date Of Birth" />
                  </Table.Td>
                ) : (
                  <Table.Td>{doctor.dob}</Table.Td>
                )}
              </Table.Tr>

              <Table.Tr>
                <Table.Td className="text-md font-medium">Phone</Table.Td>
                {editMode ? (
                  <Table.Td>
                    <NumberInput
                      maxLength={10}
                      clampBehavior="strict"
                      hideControls
                      placeholder="Phone No."
                    />
                  </Table.Td>
                ) : (
                  <Table.Td>{doctor.phone}</Table.Td>
                )}
              </Table.Tr>

              <Table.Tr>
                <Table.Td className="text-md font-medium">Address</Table.Td>
                {editMode ? (
                  <Table.Td>
                    <TextInput placeholder="Address" />
                  </Table.Td>
                ) : (
                  <Table.Td>{doctor.address}</Table.Td>
                )}
              </Table.Tr>

              <Table.Tr>
                <Table.Td className="text-md font-medium">License No</Table.Td>
                {editMode ? (
                  <Table.Td>
                    <NumberInput
                      maxLength={12}
                      clampBehavior="strict"
                      hideControls
                      placeholder="Aadhar No."
                    />
                  </Table.Td>
                ) : (
                  <Table.Td>{doctor.licenseNo}</Table.Td>
                )}
              </Table.Tr>

              <Table.Tr>
                <Table.Td className="text-md font-medium">Specialization</Table.Td>
                {editMode ? (
                  <Table.Td>
                    <Select data={doctorSpecializations} placeholder="Specialization" />
                  </Table.Td>
                ) : (
                  <Table.Td>{doctor.specialization}</Table.Td>
                )}
              </Table.Tr>

              <Table.Tr>
                <Table.Td className="text-md font-medium">Department</Table.Td>
                {editMode ? (
                  <Table.Td>
                    <Select data={doctorDepartment} placeholder="Department" />
                  </Table.Td>
                ) : (
                  <Table.Td>{doctor.department}</Table.Td>
                )}
              </Table.Tr>

              <Table.Tr>
                <Table.Td className="text-md font-medium">
                  Total Experience
                </Table.Td>
                {editMode ? (
                  <Table.Td>
                    <NumberInput
                      max={50}
                      maxLength={2}
                      clampBehavior="strict"
                      hideControls
                      placeholder="Total Experience"
                    />
                  </Table.Td>
                ) : (
                  <Table.Td>{doctor.totalExp}</Table.Td>
                )}
              </Table.Tr>
            </Table.Tbody>
          </Table>
        </Paper>
      </div>

      <Modal centered title={<span className="text-xl font-medium">Upload Profile Picture</span>} opened={opened} onClose={close} >
         
      </Modal>
    </div>
  );
};

export default Profile;
