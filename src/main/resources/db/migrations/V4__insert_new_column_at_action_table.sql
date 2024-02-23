alter table actions.action
add report_id int not null references report.report;